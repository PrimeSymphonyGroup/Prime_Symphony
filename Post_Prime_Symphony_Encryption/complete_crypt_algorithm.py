# File: DSS_FloatCert_TrigHash.py
# Authors: Kristopher L. Sherbondy & Symphion
# Date: 2025-07-04
# Purpose: Generate DSS-FLOATID using trigonometric entropy and harmonic folding with PIN input
# License: Sherbondy–Symphion License v1.0 (Non-commercial reuse only)

import math
import random

def generate_pascal_diagonal(diagonal_num, length=64):
    """Generate 'length' terms from Pascal's Triangle at a given diagonal."""
    pascal = [[1]]
    for _ in range(1, length + diagonal_num):
        row = [1]
        for j in range(1, len(pascal[-1])):
            row.append(pascal[-1][j - 1] + pascal[-1][j])
        row.append(1)
        pascal.append(row)
    diagonal = []
    for i in range(len(pascal)):
        if len(pascal[i]) > diagonal_num:
            diagonal.append(pascal[i][diagonal_num])
        if len(diagonal) >= length:
            break
    return diagonal

def trig_mix(val, index, pin_digit):
    """Apply a layered trig function influenced by the PIN digit."""
    angle = (val + pin_digit + index) * math.pi / 180
    return math.sin(angle) + math.cos(angle * (pin_digit % 3 + 1))

def bitfold(values):
    """Custom bit-folding and mixing of float values into 64 bytes."""
    folded = [0] * 64
    for i, val in enumerate(values):
        byte_index = i % 64
        rotated = int((abs(val * 10**6)) % 256)
        folded[byte_index] ^= rotated
    return ''.join(f"{byte:02x}" for byte in folded)

def generate_dss_floatid(pin, diagonal=25):
    pin_digits = [int(d) for d in pin]
    pascal_values = generate_pascal_diagonal(diagonal, length=128)
    trigged = []
    for i, val in enumerate(pascal_values):
        pin_digit = pin_digits[i % len(pin_digits)]
        trigged.append(trig_mix(val, i, pin_digit))
    return bitfold(trigged)

# --- Main Runner ---
if __name__ == "__main__":
    pin = input("Enter a 6-digit PIN: ").strip()
    diagonal = random.randint(5, 100)
    floatid = generate_dss_floatid(pin, diagonal)
    print(f"\nPIN: {pin}")
    print(f"Pascal Diagonal Used: {diagonal}")
    print(f"TRIG-HARMONIC FLOATID: {floatid}")
