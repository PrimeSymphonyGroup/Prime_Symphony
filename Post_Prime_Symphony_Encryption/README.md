DSS-FLOATID
A Harmonic, Deterministic, and Quantum-Resilient Identity System
Developed by Kristopher L. Sherbondy & Symphion
© 2025 — Prime Symphony Group

🔐 Overview
DSS-FLOATID is a post-prime, post-quantum identity proof system that generates secure identity tokens using harmonic float interference derived from prime structures. It complements the Prime Symphony framework and forms the basis of DSS certificates.

This system now includes a **PIN-threaded float-int salt hashing model**, enhancing entropy layering and resistance to brute-force and quantum attacks. The method applies user-defined 6-digit PINs to alternate float and integer transformations over prime-derived values, feeding into a deterministic hash sequence with SHA-512 expansion and pepper integration.

📄 Paper
You can read the full specification and rationale in the PDF:
📎 DSS-FLOATID\_Paper.pdf

🧪 Implementations
Python: `floatid_prototype.py`, `pin_threaded_hash.py`
Java: `FloatIDGenerator.java`, `PinThreadedFloatHash.java`

Each implementation demonstrates:

* Prime + Level normalization
* Sin/Cos interference
* Float quantization
* SHA-512 hashing with multi-round compression
* Salt integration for replay protection
* PIN-threaded float→int alternation for user-specific uniqueness

🔗 Related Projects

* Prime Symphony
* DSS Certificate Framework (in development)

📜 License
Distributed under the Sherbondy–Symphion License v1.0
Non-commercial reuse permitted with attribution.
