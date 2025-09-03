class Solution:
  def numberOfPairs(self, points: list[list[int]]) -> int:
    ans = 0

    points.sort(key=lambda x: (x[0], -x[1]))

    for i, (_, yi) in enumerate(points):
      maxY = -math.inf
      for j in range(i + 1, len(points)):
        _, yj = points[j]
        if yi >= yj > maxY:
          ans += 1
          maxY = yj

    return ans