import sys

input = sys.stdin.readline

move_to_row = [0, 0, -1, 1]
move_to_column = [1, -1, 0, 0]

n, m = map(int, input().split())

graph = []
virus = []
region_count = 0
region = []

for i in range(0, n):
    graph.append(input().split())


def find_save_region():
    queue = virus.copy()
    visited = [[False for _ in range(0, m)] for _ in range(0, n)]

    result = 0
    while len(queue) != 0:
        r, c = queue.pop(0)

        for i in range(0, 4):
            next_r = r + move_to_row[i]
            next_c = c + move_to_column[i]
            if next_r < 0 or next_r >= n or next_c < 0 or next_c >= m:
                continue
            if graph[next_r][next_c] == '0' and visited[next_r][next_c] is False:
                visited[next_r][next_c] = True
                queue.append((next_r, next_c))
                result += 1

    return region_count - result - 3


for i in range(0, n):
    for j in range(0, m):
        if graph[i][j] == '2':
            virus.append((i, j))
        elif graph[i][j] == '0':
            region_count += 1
            region.append((i, j))

answer = 0
for i in range(0, region_count):
    first_r, first_c = region[i]
    graph[first_r][first_c] = '1'
    for j in range(i + 1, region_count):
        second_r, second_c = region[j]
        graph[second_r][second_c] = '1'
        for k in range(j + 1, region_count):
            thrid_r, thrid_c = region[k]
            graph[thrid_r][thrid_c] = '1'
            answer = max(find_save_region(), answer)
            graph[thrid_r][thrid_c] = '0'
        graph[second_r][second_c] = '0'
    graph[first_r][first_c] = '0'

print(answer)
