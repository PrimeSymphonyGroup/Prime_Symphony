def is_prime(n):
    if n <= 1:
        return False
    if n <= 3:
        return True
    if n % 2 == 0 or n % 3 == 0:
        return False
    if n % 5 == 0 and n != 5:
        return False
    if n % 7 == 0 and n != 7:
        return False

    # STR-style harmonic optimization (6k ± 1 loop)
    i = 11
    while i * i <= n:
        if n % i == 0 or n % (i + 2) == 0:
            return False
        i += 6
    return True


def generate_primes(lower, upper):
    primes = []
    for num in range(lower, upper + 1):
        if is_prime(num):
            primes.append(num)
    return primes


if __name__ == "__main__":
    lower = int(input("Enter lower bound (inclusive): "))
    upper = int(input("Enter upper bound (inclusive): "))
    primes = generate_primes(lower, upper)
    print(f"Primes between {lower} and {upper}:")
    for p in primes:
        print(p)
