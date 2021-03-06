# leetcode
## Top Interview
[2.Add Two Numbers](#2add-two-numbers)  
[❖3.Longest Substring Without Repeating Characters](#3longest-substring-without-repeating-characters)  
[❖4.Median of Two Sorted Arrays](#4median-of-two-sorted-arrays)  
[5.Longest Palindromic Substring](#5longest-palindromic-substring)  
[❖20. Valid Parentheses](#20valid-parentheses)  
[53.Maximum Subarray](#53maximum-subarray)  
[56. Merge Intervals](#56merge-intervals)  
[121. Best Time to Buy and Sell Stock](#121best-time-to-buy-and-sell-stock)  
[138. Copy List with Random Pointer](#138copy-list-with-random-pointer)  
[146.LRU Cache](#146lru-cache)  
[❖206. Reverse Linked List](#206reverse-linked-list)  
[238. Product of Array Except Self](#238product-of-array-excep-self)  
[❖297. Serialize and Deserialize Binary Tree](#297serialize-and-deserialize-binary-tree)  
[380.Insert Delete GetRandom O(1)](#380insert-delete-getrandom-o1)  
[454.4Sum II](#4544sum-ii)  
### 2.Add Two Numbers
个位进位？-> 等长部分加法 -> 之后把超出部分携带进位抄进最后答案
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
```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean advance = false;
        int add = l1.val + l2.val;
        ListNode result;
        if(add<10){
            result = new ListNode(add);
        }else{
            result = new ListNode(add-10);
            advance = true;
        }
        ListNode head = result;
        while(l1.next!=null&&l2.next!=null){
            l1=l1.next;
            l2=l2.next;
            add=l1.val+l2.val;
            if(advance){
                add++;
            }
            if(add<10){
                result.next=new ListNode(add);
                advance=false;
            }
            else{
                result.next=new ListNode(add-10);
                advance=true;
            }
            result=result.next;
        }
        ListNode longer=l1;
        if(l2.next!=null){
            longer=l2;
        }
        while(longer.next!=null){
            longer=longer.next;
            add=longer.val;
            if(advance){
                add++;
            }
            if(add<10){
                result.next=new ListNode(add);
                advance=false;
            }
            else{
                result.next=new ListNode(add-10);
                advance=true;
            }
            result=result.next;
        }
        if(advance){
            result.next=new ListNode(1);
        }
        return head;
    }
}
```
### 3.Longest Substring Without Repeating Characters
use a dictionary to keep track of characters former appearence index in order to slice string after that index
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}
```

### 4.Median of Two Sorted Arrays
try to find the region between smaller median and bigger median
```python
class Solution:
    def findMedianSortedArrays(self, A: List[int], B: List[int]) -> float:
        m, n = len(A), len(B)
        if m > n:
            A,B,m,n = B,A,n,m
    
        start, end, half_len = 0, m, (m + n + 1) // 2

        while start <= end:
            x = (start + end) // 2
            y = half_len - x

            if x < m and B[y-1] > A[x]:
                # x is too small must increase it
                start = x + 1
            elif x > 0 and A[x-1] > B[y]:
                # x is too big must decrease it
                end = x - 1
            else:
                # x is good
                if x == 0: max_left = B[y-1]
                elif y == 0: max_left = A[x-1]
                else: max_left = max(A[x-1], B[y-1])

                # Check if odd or even 
                if (m + n) % 2 == 1:
                    return max_left

                if x == m: min_right = B[y]
                elif y == n: min_right = A[x]
                else: min_right = min(A[x], B[y])

                return (max_left + min_right) / 2.0
```

### 5.Longest Palindromic Substring
expand from centre in both odd even length substring
```python
class Solution(object):
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: str
        """
        def expandFromCenter(leftPoint, rightPoint):
            L = leftPoint
            R = rightPoint
            while L >= 0 and R < len(s) and s[L] == s[R]:
                L-=1
                R+=1
            return R-L-1
        start = end =0
        for i in range(len(s)):
            lenghOfOdd = expandFromCenter(i,i)
            lenghOfEven = expandFromCenter(i,i+1)
            lengthMax = max(lenghOfEven,lenghOfOdd)
            if lengthMax > end-start:
                start = i - int((lengthMax-1)/2)
                end = i + int(lengthMax/2)
        return s[start:end+1]
```

### 20.Valid Parentheses
create a stack to keep track open parentheses, when encounter a closing parenthese, check if the top is its corresponding open parenthese
```python
class Solution:
    def isValid(self, s: str) -> bool:
        stack = []
        mapping = {")": "(", "}": "{", "]": "["}
        for char in s:
            if char in mapping:
                if stack:
                    top = stack.pop()
                else:
                    top = '#'
                if mapping[char] != top:
                    return False
            else:
                stack.append(char)
        return not stack
```

### 53.Maximum Subarray
when the culmulative total decrease below 0, break the acculmulation
```python
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        for i in range(1, len(nums)):
            if nums[i-1] > 0:
                nums[i] += nums[i-1]
        return max(nums)
```

### 56.Merge Intervals
create stack to keep track the latest non-overlap interval, if any sorted intervals overlap it, extend the end to the overlap interval's end.
```python
class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        intervals.sort(key=lambda x: x[0])

        merged = []
        for interval in intervals:
            # if the list of merged intervals is empty or if the current
            # interval does not overlap with the previous, simply append it.
            if not merged or merged[-1][1] < interval[0]:
                merged.append(interval)
            else:
            # otherwise, there is overlap, so we merge the current and previous
            # intervals.
                merged[-1][1] = max(merged[-1][1], interval[1])

        return merged
```

### 121.Best Time to Buy and Sell Stock
if price is lower update minprice or profit is higher than maxprofit, update maxprofit
```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        minprice = float('inf')
        maxprofit = 0
        for i in range(len(prices)):
            if prices[i]<minprice:
                minprice = prices[i]
            elif prices[i] - minprice > maxprofit:
                maxprofit = prices[i] - minprice
        return maxprofit
```

### 138. Copy List with Random Pointer
initialize nodes in a dic, and link nodes via dic key-values
```python
class Solution:
    def copyRandomList(self, head: 'Node') -> 'Node':
        dic = dict()
        m = n = head
        while m:
            dic[m] = Node(m.val,m.next,m.random)
            m = m.next
        while n:
            dic[n].next = dic.get(n.next)
            dic[n].random = dic.get(n.random)
            n = n.next
        return dic.get(head)
```

### 146.LRU Cache
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
```java
class DoubleLink(): 
    def __init__(self, key=0, value=0):
        self.key = key
        self.value = value
        self.prev = None
        self.next = None
            
class LRUCache():
    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.capacity = capacity
        self.size = 0
        self.cacheDict = {}
        self.head = DoubleLink()
        self.tail = DoubleLink()
        
        self.head.next = self.tail
        self.tail.prev = self.head
        
    def _add_node(self, newNode):
        """
        Always add the new node right after head.
        """
        endNode = self.tail.prev
        endNode.next = newNode
        newNode.prev = endNode
        newNode.next = self.tail
        self.tail.prev = newNode

    def _remove_node(self, node):
        """
        Remove an existing node from the linked list.
        """
        prevNode = node.prev
        nextNode = node.next

        prevNode.next = nextNode
        nextNode.prev = prevNode
        
        node = None
        
    def _move_to_right(self, node):
        """
        Move certain node in between to the head.
        """
        self._remove_node(node)
        self._add_node(node)

    def _remove_least_used_node(self):
        """
        Pop the current tail.
        """
        firstNode = self.head.next
        
        self._remove_node(firstNode)
        
        firstNode = None
        
    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        if key in self.cacheDict:
            curNode = self.cacheDict[key]
            self._move_to_right(curNode)
            return curNode.value
        else:
            return -1

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: void
        """
        if key in self.cacheDict:
            curNode = self.cacheDict[key]
            curNode.value = value
            self._move_to_right(curNode)
            
        else:
            # not exist , add one
            newNode = DoubleLink(key, value)
            self.cacheDict[key] = newNode
            self._add_node(newNode)
            self.size += 1
                
            if self.size > self.capacity:
                # full, remove least used node
                leastNode = self.head.next
                del self.cacheDict[leastNode.key]
                self._remove_least_used_node()
                
                self.size -= 1
```

### 206.Reverse Linked List
put current next node on hold, link current next node to previos node, then set current node to current next node
```python
class Solution:
    def reverseList(self, head: ListNode) -> ListNode:
        prev = None
        curr = head
        while curr:
            nextNode = curr.next
            curr.next = prev
            prev = curr
            curr = nextNode
        return prev
```

### 238.Product of Array Except Self
compute a left product, to compute its left nums product except itself, then a right product, and multiply element-wise two products array
```python
class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        L,R = [1]*len(nums)
        for i in range(len(nums)-1):
            L[i+1] = L[i]*nums[i]
        R = [1]*len(nums)
        for i in range(len(nums)-1,0,-1):
            R[i-1] = R[i] * nums[i]
        for i in range(len(L)):
            L[i] = L[i]*R[i]
        return L
```

### 297.Serialize and Deserialize Binary Tree
done using dfs:  
when serializing use queue to dfs  
when deserializing check index+1 for left position and index+2 for right position
```python
class Codec:

    def serialize(self, root):
        """Encodes a tree to a single string.
        
        :type root: TreeNode
        :rtype: str
        """
        q = []
        q.append(root)
        lst = []
        while q:
            node = q.pop(0)
            if node is None:
                lst.append('#')
            else:
                lst.append(str(node.val))
                q.append(node.left)
                q.append(node.right)
        print(lst)
        return ','.join(lst)

    def deserialize(self, data):
        """Decodes your encoded data to tree.
        
        :type data: str
        :rtype: TreeNode
        """
        if not data or data=='#': return None
        nodes = data.split(',')
        root = TreeNode(int(nodes[0]))
        q = []
        q.append(root)
        index = 1
        while q:
            node = q.pop(0)
            if nodes[index] is not '#':
                node.left = TreeNode(int(nodes[index]))
                q.append(node.left)
            index += 1
        
            if nodes[index] is not '#':
                node.right = TreeNode(int(nodes[index]))
                q.append(node.right)
            index += 1
        return root
```

### 380.Insert Delete GetRandom O(1)
use map hashing to fast check existence
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

### 454.4Sum II
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