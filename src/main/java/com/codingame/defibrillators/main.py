import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

lon = float(raw_input().replace(',','.')) * math.pi / 180.
lat = float(raw_input().replace(',','.')) * math.pi / 180.
n = int(raw_input())
nodes = []
for i in xrange(n):
    defib = raw_input()
    tokens = defib.split(';')
    node = {}
    node['number'] = int(tokens[0]);
    node['name'] = tokens[1];
    node['address'] = tokens[2];
    node['phone'] = tokens[3];
    node['lon'] = float(tokens[4].replace(",", ".")) * math.pi / 180.
    node['lat'] = float(tokens[5].replace(",", ".")) * math.pi / 180.
    nodes += [node]

answer = None
dist = 1 << 31
for n in nodes:
    x = (n['lon']-lon)*math.cos(.5*(lat+n['lat']))
    y = n['lat'] - lat
    d = math.sqrt(x * x + y * y) * 6371
    if d < dist:
        dist = d
        answer = n

print answer['name']
