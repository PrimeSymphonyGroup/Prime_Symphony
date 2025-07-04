import hashlib

def pin_threaded_salt(prime, level, float_seed, pin, scaling=1e8, mod_prime=982451653):
    """
    Generates a salt by applying each digit of a 6-digit PIN to alternating float-int transformations.
    """
    # Ensure pin is 6 digits (pad with zeroes if needed)
    pin_digits = str(pin).zfill(6)

    # Start with initial float value
    value = prime / level + float_seed

    for i, digit_char in enumerate(pin_digits):
        digit = int(digit_char) + 1  # Avoid zero multiplier

        if i % 2 == 0:
            # FLOAT → INT step
            value = int(value * digit * scaling) % mod_prime
        else:
            # INT → FLOAT step
            value = value / (level * float_seed * digit)

    # Hash the final value to produce the salt
    return hashlib.sha256(str(value).encode()).hexdigest()


def compress_expand_cycle(salt, password, pepper="", rounds=3):
    """
    Repeatedly expands the entropy by hashing with SHA-512.
    """
    current = hashlib.sha256(salt.encode()).hexdigest()
    for _ in range(rounds):
        current = hashlib.sha512((current + password + pepper).encode()).hexdigest()
    return current


def final_hash_with_pin_threading(prime, level, password, pin, float_seed=0.1337, pepper="QuantumGuard2025"):
    """
    Generates a deterministic, PIN-threaded final hash using the float/int alternation model.
    """
    # Step 1: PIN-threaded salt
    salt = pin_threaded_salt(prime, level, float_seed, pin)

    # Step 2: Evolve entropy through expansion rounds
    evolved = compress_expand_cycle(salt, password, pepper)

    # Step 3: Final hash with PIN mixed in
    final = hashlib.sha512((evolved + str(pin)).encode()).hexdigest()
    return final


# Example Usage
if __name__ == "__main__":
    prime = 101
    level = 7
    password = "hunter2"
    pin = "734928"
    float_seed = 0.4142
    pepper = "QuantumGuard2025"

    floatid_hash = final_hash_with_pin_threading(prime, level, password, pin, float_seed, pepper)
    print("FLOATID:", floatid_hash)
