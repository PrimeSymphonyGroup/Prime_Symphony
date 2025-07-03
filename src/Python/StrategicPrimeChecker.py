# test_str_prime_generator.py

import unittest
from str_prime_generator import generate_primes

class TestSTRPrimeGenerator(unittest.TestCase):

    def test_first_10_primes(self):
        expected = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]
        result = generate_primes(1, 30)
        self.assertEqual(result[:10], expected)

    def test_range_100_120(self):
        expected = [101, 103, 107, 109, 113]
        result = generate_primes(100, 120)
        self.assertEqual(result, expected)

    def test_no_primes_in_range(self):
        result = generate_primes(90, 96)
        self.assertEqual(result, [])

    def test_single_prime_edge(self):
        result = generate_primes(29, 29)
        self.assertEqual(result, [29])

    def test_invalid_range(self):
        result = generate_primes(20, 10)
        self.assertEqual(result, [])

    def test_negative_range(self):
        result = generate_primes(-10, 10)
        self.assertEqual(result, [2, 3, 5, 7])

if __name__ == '__main__':
    unittest.main()
