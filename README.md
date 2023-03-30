# 301a1
Assignment One - External Merge Sort

# Description
The goal of this assignment is to understand and implement an external merge sort algorithm using MinHeaps.

by Kyle Ananayo and Hannah Carino

# Progress report
- Tue 14 March: MyMinHeap now working correctly. Main file created to test its functionality.
- Wed 15 March: CreateRuns main() and populateHeap() functions created. Correctly populates heap.
  - Notes: this can be tested by compiling then running `cat dummyData.txt | java CreateRuns 10`
  - Added helper method shortenScope() to MyMinHeap to temporarily shorted the scope of MinHeap
- Sat 18 March: CreateRuns complete implementation. It should read an interger value from the standard input arguments and create a heap of that size. Creates variable length runs.
  - Added reheap() that makes use of upheap(). *Needs refactoring as it may not be very efficient*
  - Added helper methods for debugging
- Mon 27 March: completed implementation of DistributeRuns that distributes data into k-number of files.
  - Added code into Main.java for testing implementation
  - `cat MobyDick.txt | java CreateRuns 10 | java Main 4` will distribute the CreateRuns output into 4 files.
- Mon 27 March: impemented Node and NodeMinHeap in order to keep track of lines and what files they belong to. Required for MergeSort.java to be implemented soon.
  - TODO: refactor reheap() to start from the middle of the heap array.
- Tue 28 March: Primary implementation for MergeRuns(). Merges data using external merge sort algorithm. Makes use of DistributeRuns.
  - usage: `cat MobyDick.txt | java CreateRuns <min heap size> | java MergeRuns <number of files>`
- Wednesday 29 March:
  - helper functions to print to standard output (console) and print to file
  - Delete temporary files on exit

# TODO:
- Make reheap() more efficient. It currently upheaps every single node, but it shouldn't need to. It should start in the middle (according to tony) and downheap (?)
- Remove the endOfRunFlag from the output of the final file in MergeRuns
- Delete all empty files from MergeRuns algorithm. Tried using the helper function removeEmptyFiles() but it won't remove them due to a single "" being printed when the PrintWriter is closed.
