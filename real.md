### surface area of [[]]
```python
def surfaceArea(mat):
    if not mat: return 0
    x,y = [len(mat),len(mat[0])]
    res = 0
    adjs = [[1,0],[-1,0],[0,-1],[0,1]]
    for i in range(x):
        for j in range(y):
            block = mat[i][j]
            res+=2 if block>0 else 0
            res+=block if i-1<0 else 0
            res+=block if j-1<0 else 0
            res+=block if i+1==x else 0
            res+=block if j+1==y else 0
            for adj in adjs:
                if(i+adj[0]>=0 and i+adj[0]<x and j+adj[1]>=0 and j+adj[1]<y):
                    adj_size = mat[i+adj[0]][j+adj[1]]
                    diff = block - adj_size
                    res+=diff if diff>0 else 0
            
    return res
 ```
