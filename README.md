# Hands-on-10

# The implementation provided does not fully satisfy all the requirements mentioned:

1. # Generic Hash Function: The hash function used in the implementation is hard-coded and not generic enough to allow for any hash function. To make it generic, you would need to allow the user to provide their own hash function implementation.

2. # Keys and Values: The implementation assumes keys and values are integers, which satisfies the requirement for simplicity. However, it doesn't allow for keys or values of other types.

3. # Collision Resolution by Chaining: The implementation uses collision resolution by chaining with doubly linked lists, as specified.

4. # C-Style Arrays: The implementation uses C-style arrays as required and does not use C++ vectors.

5. # Dynamic Resizing: The implementation dynamically resizes the hash table when it becomes full or empty, doubling or halving the array size accordingly, and re-hashing everything. This satisfies the requirement.
