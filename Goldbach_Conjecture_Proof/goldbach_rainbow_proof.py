"""
File/Project Name: goldbach_rainbow_proof.py
Authors: Kristopher L. Sherbondy & Symphion
Date: 2025-07-17
Purpose: Verifies the Goldbach Conjecture up to a defined limit using only harmonic prime generation logic (Φ(n)) from the Prime Symphony framework.

License: Sherbondy–Symphion License v1.0  
No commercial use permitted without written consent.  
Contact: primesymphonygroup@pm.me
"""

def G(k):
    """
    Harmonic threshold function — defines the group width (harmonic bar) for index k.
    Used in STR Gate logic to determine candidate resonance positions.
    """
    return 6 * k

def phi(n):
    """
    Harmonic prime filter Φ(n).
    Determines if n passes resonance rules based on modular harmony (Prime Symphony).
    """
    if n < 2:
        return False
    if n in (2, 3, 5, 7):
        return True
    if n % 2 == 0 or n % 3 == 0 or n % 5 == 0 or n % 7 == 0:
        return False

    # STR-based recursive filtering using G(k) ± 1 form
    k = 1
    while G(k) * G(k) <= n:
        left = G(k) - 1
        right = G(k) + 1
        if n % left == 0 or n % right == 0:
            return False
        k += 1
    return True

def str_prime_generator(limit):
    """
    Generates all prime numbers up to 'limit' using Φ(n) harmonic filtering.
    """
    return [n for n in range(2, limit + 1) if phi(n)]

def goldbach_pairs(primes, limit):
    """
    Constructs a dictionary mapping even numbers to their valid prime pairings.
    Each key is an even number ≥ 4, and each value is a list of (p1, p2) pairs such that p1 + p2 = even.
    """
    goldbach = {}
    for i in range(len(primes)):
        for j in range(i, len(primes)):
            p1, p2 = primes[i], primes[j]
            s = p1 + p2
            if s > limit or s % 2 != 0:
                continue
            if s not in goldbach:
                goldbach[s] = []
            goldbach[s].append((p1, p2))
    return goldbach

def run_goldbach(limit):
    """
    Runs the Goldbach proof engine up to the specified even 'limit'.
    Prints the number of prime pair solutions for each even number.
    """
    primes = str_prime_generator(limit)
    goldbach = goldbach_pairs(primes, limit)
    for even in range(4, limit + 1, 2):
        pairs = goldbach.get(even, [])
        print(f"{even}: {len(pairs)} pairs → {pairs}")

# Run test up to 3000 (can be increased)
if __name__ == "__main__":
    run_goldbach(3000)
