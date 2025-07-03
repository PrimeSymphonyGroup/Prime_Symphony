def generate_primes(lower, upper):
    if upper < 2 or lower > upper:
        return []

    primes = []
    for n in range(max(2, lower), upper + 1):
        if n in (2, 3, 5, 7):  # base primes
            primes.append(n)
            continue
        if n % 2 == 0 or n % 3 == 0 or n % 5 == 0 or n % 7 == 0:
            continue
        if (n - 1) % 6 != 0 and (n + 1) % 6 != 0:
            continue

        # 6k ± 1 filter — minimal trial division with harmonic rhythm
        is_str_prime = True
        k = 11
        while k * k <= n:
            if n % k == 0 or n % (k + 2) == 0:
                is_str_prime = False
                break
            k += 6

        if is_str_prime:
            primes.append(n)

    return primes
