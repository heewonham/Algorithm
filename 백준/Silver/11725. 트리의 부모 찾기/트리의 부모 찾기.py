import sys
input = sys.stdin.readline

n = input()

nodes = [[] for _ in range(0, int(n)+1)]
parent = [0 for _ in range(0, int(n)+1)]

def find_parent(start: int):
    stack = [start]
    parent[start] = start

    while len(stack) != 0:
        cur = stack.pop(len(stack)-1)
        for i in nodes[cur]:
            if parent[cur] == i:
                continue
            parent[i] = cur
            stack.append(i)

for i in range(1, int(n)):
    v1, v2 = map(int, input().split())
    nodes[v1].append(v2)
    nodes[v2].append(v1)

find_parent(1)

for i in range(2, int(n)+1):
    print(parent[i])
