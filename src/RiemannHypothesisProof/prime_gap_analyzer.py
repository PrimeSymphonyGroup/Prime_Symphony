# Author: Kristopher L. Sherbondy & Symphion
# Date: 2025-07-04
# Purpose: Analyze prime gaps from CSV and overlay harmonic patterns

import csv
import matplotlib.pyplot as plt
import numpy as np
from collections import Counter

# Load prime gaps from CSV
def load_gaps(filename):
    gaps = []
    with open(filename, 'r') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            gaps.append(int(row['Gap']))
    return gaps

# Plot histogram and overlay harmonic resonance

def plot_gap_histogram(gaps):
    counter = Counter(gaps)
    x_vals = sorted(counter.keys())
    y_vals = [counter[x] for x in x_vals]

    plt.figure(figsize=(12, 6))
    plt.bar(x_vals, y_vals, label='Prime Gaps')

    # Harmonic overlay: sine^2 fit with log decay
    x_overlay = np.linspace(min(x_vals), max(x_vals), 1000)
    sine_wave = np.sin(np.pi * x_overlay / max(x_vals))**2
    decay = 1 / np.log(x_overlay + 2)
    overlay = sine_wave * decay * max(y_vals)

    plt.plot(x_overlay, overlay, 'r--', label='Harmonic × Log Decay')

    plt.title("Prime Gap Frequency with Harmonic Overlay")
    plt.xlabel("Gap Size")
    plt.ylabel("Frequency")
    plt.legend()
    plt.tight_layout()
    plt.savefig("resonance_decay_overlay_py.png")
    plt.show()

if __name__ == '__main__':
    gaps = load_gaps('prime_gaps.csv')
    plot_gap_histogram(gaps)
    print("Chart saved as resonance_decay_overlay_py.png")
