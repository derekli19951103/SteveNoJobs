# leetcode
## Top Interview
[2.Add-Two-Numbers](#2add-two-numbers)  
[146.LRU-Cache](#146lru-cache)
[380.Insert-Delete-GetRandom-O(1)](#380insert-delete-getrandom-o1)  
[454.4Sum-II](#4544sum-ii)  
### 2.Add-Two-Numbers
个位进位？判断等长部分加法：直接返回（说明是单个数字相加）-> 之后把超出部分携带进位抄进最后答案
```python
class Solution(object):
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        advance=False
        added=l1.val+l2.val
        if added<10:
            result=ListNode(added)
        else:
            result=ListNode(added-10)
            advance=True
        head=result
        while l1.next and l2.next:
            l1=l1.next
            l2=l2.next
            added=l1.val+l2.val
            if advance:
                added+=1
            if added<10:
                result.next=ListNode(added)
                advance=False
            else:
                result.next=ListNode(added-10)
                advance=True
            result=result.next
        longer=l1
        if l2.next:
            longer=l2
        while longer.next:
            longer=longer.next
            added=longer.val
            if advance:
                added+=1
            if added<10:
                result.next=ListNode(added)
                advance=False
            else:
                result.next=ListNode(added-10)
                advance=True
            result=result.next
        if advance:
            result.next=ListNode(1)
        return head
```

### 146.LRU-Cache
keep an order of usage in order to evict when putting  
optimization: use a second hashmap to store order index
```python
class LRUCache:

    def __init__(self, capacity: int):
        self.cache = {}
        self.capacity = capacity
        self.order = []

    def get(self, key: int) -> int:
        try:
            value = self.cache[key]
            self.order.remove(key)
            self.order.append(key)
            return value
        except (KeyError,ValueError) as e:
            return -1

    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            self.cache[key] = value
            self.order.remove(key)
            self.order.append(key)
        else:
            if len(self.cache) < self.capacity:
                self.cache[key] = value
                self.order.append(key)
            else:
                try:
                    evicted = self.order.pop(0)
                    del self.cache[evicted]
                    self.cache[key] = value
                    self.order.append(key)
                except KeyError:
                    pass
```

### 380.Insert-Delete-GetRandom-O(1)
use map hashing to fast check exixstence
```python
import random
class RandomizedSet:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.container={}
        self.real_set=[]

    def insert(self, val: int) -> bool:
        """
        Inserts a value to the set. Returns true if the set did not already contain the specified element.
        """
        try:
            v = self.container[val]
        except KeyError:
            self.container[val]=val
            self.real_set.append(val)
            return True
        return False
            
    def remove(self, val: int) -> bool:
        """
        Removes a value from the set. Returns true if the set contained the specified element.
        """
        try:
            del self.container[val]
        except KeyError:
            return False
        self.real_set.remove(val)
        return True
        
    def getRandom(self) -> int:
        """
        Get a random element from the set.
        """
        return random.choice(self.real_set)
```

### 454.4Sum-II
construct counter parts of sum of two arrays with the other two's sum
```python
class Solution:
    def fourSumCount(self, A: List[int], B: List[int], C: List[int], D: List[int]) -> int:
        count = 0
        dictCD = {}
        for c in C:
            for d in D:
                if c+d in dictCD:
                    dictCD[c+d]+=1
                else:
                    dictCD[c+d]=1
        for a in A:
            for b in B:
                if -(a+b) in dictCD:
                    count += dictCD[-(a+b)]
        return count
```