# 301a1
Assignment One - External Merge Sort

# Progress report
- Tue 14 March: MyMinHeap now working correctly. Main file created to test its functionality.
- Wed 15 March: CreateRuns main() and populateHeap() functions created. Correctly populates heap.
  - Notes: this can be tested by compiling then running `cat dummyData.txt | java CreateRuns 10`
  - Added helper method shortenScope() to MyMinHeap to temporarily shorted the scope of MinHeap
- Sat 18 March: CreateRuns complete implementation. It should read an interger value from the standard input arguments and create a heap of that size. Creates variable length runs.
  - Added reheap() that makes use of upheap(). *Needs refactoring as it may not be very efficient*
  - Added helper methods for debugging
