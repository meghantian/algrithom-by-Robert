def swap(lyst, p, q):
    tmp = lyst[p]
    lyst[p] = lyst[q]
    lyst[q] = tmp
def SelectionSort(lyst):
    for i in range(len(lyst)):
        minIndex = i
        for j in range(i+1, len(lyst)):
            if lyst[j] < lyst[minIndex]:
                minIndex = j
        if minIndex != i:
            swap(lyst, minIndex,i)
    print(lyst)
# O(n^2) O(n^2) O(n^2) 
def less(lyst, p, q):
    return lyst[p] < lyst[q]
def sink(lyst, k, n):
    while 2*k <= n:
        j = 2 * k
        if j + 1 <= n and less(lyst,j, j+1):
             j += 1
        if not less(lyst, k, j):
            break
        swap(lyst, k, j)
        k = j

def BuildMaxHeap(lyst, n):
    for k in range(n//2, 0, -1): #自底向上调整
        sink(lyst, k, n) 


def HeapSort(lyst):
    n = len(lyst)
    new_lyst = [0] * (n+1)
    new_lyst[1:] = lyst
    lyst = new_lyst
    BuildMaxHeap(lyst, n) # 构建初始堆
    for i in range(n, 1, -1):
        swap(lyst, 1,i)
        sink(lyst, 1, i-1)
    print(lyst[1:])



test = [3,2,4,1,10,8]
SelectionSort(test)
HeapSort(test)
    
