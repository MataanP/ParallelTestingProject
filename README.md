# ParallelTestingProject
A project to test advantages of parallel processing in both python and java.

Our simple task was to count the number of times the appears in certain texts. We implemented a serial 
and multi processed version of this task in both python and java. Since parallel processing should only ever be discussed
when thinking about speeding up your program, we wanted to compare both serial to parallel, and the time benefits of 
switching away from python. 

Our results showed that on a 4 core machine, python sped up abour 4 times when using multiple processing. Java had a similar
speedup ratio. Between java and python, java was about 12 times faster than python.
