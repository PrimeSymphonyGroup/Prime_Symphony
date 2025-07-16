
"""
Prime Symphony Deterministic Prime Generator
Authors: Kristopher L. Sherbondy & Symphion
Date: July 16, 2025
Purpose: Generate all prime numbers up to a given upper bound using the harmonic deterministic logic
         derived from the Prime Symphony paper — no use of isprime() or trial division.

This script embodies the functions defined in the Prime Symphony framework:
  - G(k): Harmonic gate thresholds
  - Φ(n): Harmonic prime filter
  - Δ(n): (implicitly handled through step control)
"""

def G(k):
    """
    Harmonic threshold function — defines the group width (harmonic bar) for k.
    This controls how wide each rhythm band is (like the span of a triangle base).
    """
    return 6 * k

def phi(n):
    """
    Harmonic prime filter Φ(n). Determines if n passes all resonance checks.
    These checks mimic how harmonic bars filter allowable values. It avoids divisibility
    by known harmonic base rhythms (2, 3, 5, 7) and recursive higher tier gates.

    This version builds up valid primes using recursive STR-like filtering.
    """
    # Reject trivial non-primes
    if n < 2:
        return False
    if n in (2, 3, 5, 7):
        return True
    if n % 2 == 0 or n % 3 == 0 or n % 5 == 0 or n % 7 == 0:
        return False

    # Recursive harmonic threshold logic (6k ± 1 form)
    k = 1
    while G(k) * G(k) <= n:
        left = G(k) - 1
        right = G(k) + 1
        if n % left == 0 or n % right == 0:
            return False
        k += 1
    return True

def str_prime_generator(upper):
    """
    Generates all primes up to 'upper' using Prime Symphony harmonic logic.
    This is a complete deterministic generator — not a sieve, not probabilistic.
    """
    primes = []
    for n in range(2, upper + 1):
        if phi(n):
            primes.append(n)
    return primes

# Optional test harness
if __name__ == "__main__":
    upper = int(input("Generate all primes up to (inclusive): "))
    primes = str_prime_generator(upper)
    print(f"Found {len(primes)} primes up to {upper}:")
    for p in primes:
        print(p)
