import math
import hashlib

# Generate Pascal Triangle diagonals from 5 to 100
def generate_pascals_diagonal(start=5, end=100):
    diagonals = {}
    for d in range(start, end + 1):
        diagonal = []
        for n in range(d, 201):  # Arbitrary range for depth
            value = math.comb(n, d)
            diagonal.append(value)
        diagonals[d] = diagonal
    return diagonals

# Harmonic Hash Engine
def harmonic_hash(input_string, pin="123456", rounds=64):
    diagonals = generate_pascals_diagonal(5, 100)
    hash_material = input_string + pin
    accumulator = 0.0

    for i in range(rounds):
        pin_digit = int(pin[i % len(pin)])
        diag_index = 5 + (i * pin_digit) % 96  # Ensures diagonal stays between 5–100
        diagonal = diagonals[diag_index]
        harmonic = math.sin(accumulator + pin_digit) + math.cos(accumulator + pin_digit)
        mod_val = diagonal[i % len(diagonal)] % (pin_digit + 1)
        accumulator += harmonic * mod_val

        # Alternate float/int threading
        char = hash_material[i % len(hash_material)]
        if i % 2 == 0:
            accumulator *= float(ord(char)) / (pin_digit + 1)
        else:
            accumulator += int(ord(char)) * pin_digit

    # Optional SHA-512 post-compression
    float_bytes = str(accumulator).encode('utf-8')
    final_hash = hashlib.sha512(float_bytes).hexdigest()

    return final_hash

# Example run
if __name__ == "__main__":
    input_data = "DSS-PrimeSymphony-Test"
    user_pin = "582947"
    result = harmonic_hash(input_data, user_pin)
    print("HARMONIC HASH:", result)
