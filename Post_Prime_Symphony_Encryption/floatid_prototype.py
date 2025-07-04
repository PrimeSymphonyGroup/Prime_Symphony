import math
import hashlib
import time

def generate_floatid(p1, L1, p2, L2, salt=None):
    # Step 1: Normalize primes by their harmonic levels
    r1 = p1 / L1
    r2 = p2 / L2

    # Step 2: Interference function using sin and cos
    interference = (math.sin(math.pi * r1) + math.cos(math.pi * r2)) % 1.0

    # Step 3: Quantize to integer scale (12 digits of precision)
    quantized = int(interference * 1_000_000_000_000)

    # Step 4: Generate salt if not provided
    if salt is None:
        salt = str(int(time.time()))  # Default: Unix timestamp

    # Step 5: Create hash (FLOATID)
    combined = f"{quantized}|{salt}".encode()
    floatid = hashlib.sha512(combined).hexdigest()

    return floatid

# Example usage
if __name__ == "__main__":
    # Test primes and levels (choose arbitrarily or based on harmonic band)
    p1 = 113
    L1 = 8
    p2 = 197
    L2 = 14
    salt = "deviceXYZ-session001"

    fid = generate_floatid(p1, L1, p2, L2, salt)
    print("FLOATID:", fid)
