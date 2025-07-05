# Author: Kristopher L. Sherbondy & Symphion
# Date: 2025-07-04
# Purpose: Deterministically generate Mersenne primes using only STR-style harmonic filters
# License: Sherbondy–Symphion License v1.0 (non-commercial reuse with attribution)

def is_str_prime(n):
    if n <= 1:
        return False
    if n in (2, 3, 5, 7):
        return True
    if n % 2 == 0 or n % 3 == 0 or n % 5 == 0 or n % 7 == 0:
        return False
    i = 11
    while i * i <= n:
        if n % i == 0 or n % (i + 2) == 0:
            return False
        i += 6
    return True

def generate_mersenne_primes(max_exponent):
    print("Diamond-Verified Mersenne Primes:")
    for p in range(2, max_exponent + 1):
        if is_str_prime(p):
            candidate = (1 << p) - 1
            if candidate <= 2**31 - 1 and is_str_prime(candidate):
                print(f"p = {p} => 2^{p} - 1 = {candidate}")

if __name__ == "__main__":
    generate_mersenne_primes(31)
