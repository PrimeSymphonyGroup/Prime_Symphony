# Author: Kristopher L. Sherbondy & Symphion
# Date: 2025-07-04
# Purpose: Generate prime numbers and output their consecutive prime gaps to CSV

import csv
import math

# Efficient prime check using STR-style harmonic optimization
def is_prime(n):
    if n < 2:
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

def generate_primes(limit):
    primes = []
    num = 2
    while len(primes) < limit:
        if is_prime(num):
            primes.append(num)
        num += 1
    return primes

def write_prime_gaps(primes, filename):
    with open(filename, 'w', newline='') as csvfile:
        writer = csv.writer(csvfile)
        writer.writerow(['Index', 'Prime', 'Gap'])
        for i in range(1, len(primes)):
            gap = primes[i] - primes[i - 1]
            writer.writerow([i, primes[i], gap])

if __name__ == '__main__':
    limit = 10000  # Change this to generate more primes
    primes = generate_primes(limit)
    write_prime_gaps(primes, 'prime_gaps.csv')
    print(f"Generated {limit} primes and saved gaps to prime_gaps.csv")
