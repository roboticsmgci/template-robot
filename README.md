# WARNING: Make sure you deploy to the right robot!

Deploying to the wrong robot will permanently damage the motors. This code is designed for **brushless motors**. 

This repo is an example/template for future robots. Make sure to update libraries and fix breaking changes for future editions.

---

# Contributing and Style Guide

The rules are generally lax, but here are a few ground rules.

 - The `main` branch should be updated with the most recent working code on the robot
 - Create a branch/fork to test new code, then merge into `main` once it works and is permanent
 - Commands must be ordered like the following:
    - `commands/auto/` has commands used in autonomous
    - `commands/common/` has complex command groups used often
    - `commands/` has lower level commands
 - Includes must be ordered alphabetically, in categories by this order: header, third-party libraries, first-party include
 - Variables are named in camelCase
   - No prefix for member variables (WPILib uses `m_`)
   - All caps for constants (WPILib uses `k_`)
   - `_` prefix for other (WPILib uses none)
 - Follow the [PEP 8](https://peps.python.org/pep-0008/) style guide