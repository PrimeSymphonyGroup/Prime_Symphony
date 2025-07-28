# Author: Kristopher L. Sherbondy & Symphion
# Date: 2025-07-25
# Purpose: Deterministic prime generator with delta tracking (PSH-compliant)
# Enhanced: Chunked output, safe write mode, UTF-8 encoding, progress logging
# License: Sherbondy–Symphion License v1.0

import time
import tracemalloc
import os

def Phi_filter(n, primes):
    for p in primes:
        if p * p > n:
            break
        if n % p == 0:
            return False
    return True

def G_k(limit):
    k = 1
    while True:
        g1 = 6 * k - 1
        g2 = 6 * k + 1
        if g1 > limit and g2 > limit:
            break
        if g1 <= limit:
            yield g1
        if g2 <= limit:
            yield g2
        k += 1

def generate_primes(limit):
    primes = [2, 3, 5]
    deltas = [None, 1, 2]
    for n in G_k(limit):
        if n <= primes[-1]:
            continue
        if Phi_filter(n, primes):
            delta = n - primes[-1]
            primes.append(n)
            deltas.append(delta)
    return primes, deltas

def write_chunk(primes, start_idx, end_idx, chunk_num):
    filename_tmp = f"psh_primes_{chunk_num}.tmp"
    filename_final = f"psh_primes_{chunk_num}.txt"
    with open(filename_tmp, "w", encoding="utf-8") as f:
        f.write(f"Chunk {chunk_num}: Primes {start_idx} to {end_idx - 1}\n")
        f.write(", ".join(str(p) for p in primes[start_idx:end_idx]) + "\n")
        f.flush()
    os.replace(filename_tmp, filename_final)

def write_progress_log(last_prime):
    with open("progress_log.txt", "w", encoding="utf-8") as f:
        f.write(f"Last successfully written prime: {last_prime}\n")

def write_summary(primes, deltas, limit, exec_time, current_mem, peak_mem):
    with open("psh_summary.txt", "w", encoding="utf-8") as f:
        f.write(f"Found {len(primes)} primes up to {limit}\n")
        f.write("Delta Lineage (first 20 primes):\n")
        for i in range(min(20, len(primes))):
            f.write(f"  Prime {primes[i]} (Delta = {deltas[i]})\n")
        f.write(f"\nExecution Time: {exec_time:.6f} seconds\n")
        f.write(f"Current Memory Usage: {current_mem / 10**6:.6f} MB\n")
        f.write(f"Peak Memory Usage: {peak_mem / 10**6:.6f} MB\n")

if __name__ == "__main__":
    limit = 100_000_000
    chunk_size = 1_000_000

    print(f"Generating primes up to {limit}...")
    start_time = time.time()
    tracemalloc.start()

    primes, deltas = generate_primes(limit)

    end_time = time.time()
    current, peak = tracemalloc.get_traced_memory()
    tracemalloc.stop()
    exec_time = end_time - start_time

    # Write summary
    write_summary(primes, deltas, limit, exec_time, current, peak)

    # Write primes in chunks
    total = len(primes)
    for i in range(0, total, chunk_size):
        start_idx = i
        end_idx = min(i + chunk_size, total)
        chunk_num = f"{start_idx}_{end_idx}"
        write_chunk(primes, start_idx, end_idx, chunk_num)
        write_progress_log(primes[end_idx - 1])

    print("✅ All files written successfully.")
