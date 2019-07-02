def getDict(s):
	dic={}
	for char in s:
		if char in dic:
			dic[char]+=1
		else:
			dic[char]=1
	return dic

def getMinimumDifference(a,b):
	if len(a)!=len(b) or (not a and not b):
		return -1
	count = 0
	map_a = getDict(a)
	for i in range(len(b)):
		if b[i] in map_a:
			map_a[b[i]]-=1
			if(map_a[b[i]]<0):
				count+=1
		else:
			count+=1
	return count
	
def getMinimumDifferences(a,b):
	return [getMinimumDifference(a[i],b[i]) for i in range(len(a))]

print(getMinimumDifferences(['a','jk','abb','mn','abc'],['bb','kj','bbc','op','def']))
print(getMinimumDifferences(['ab'],['bc']))
print(getMinimumDifferences(['abc','aaa'],['bba','bbb']))
