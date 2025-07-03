class Solution:
    def convertToTitle(self, n: int) -> str:
        result = []

        while n > 0:
            n -= 1  # Adjust for 1-based indexing
            result.append(chr((n % 26) + ord('A')))
            n //= 26

        return ''.join(reversed(result))
