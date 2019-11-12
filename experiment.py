import multiprocessing as mp
import sys
import time
import os
import timeit

def count_words(fileName,targetWord,procnum = -1,ret_dict = None):
    text = open(fileName)
    symbols = [';', ':', '!', ",", ".", "(", ")", "?", "'"]
    # counter keeping track of number of occurences
    count = 0

    # Loop through each line
    for line in text:
        # Remove the leading spaces and newline character
        line = line.strip()

        #remove all symbols in a line
        for i in symbols:
            line = line.replace(i, " ")

        # Convert the all words to lowercase
        line = line.lower()

        # Split the line into words
        words = line.split(" ")

        # Iterate over each word in line
        for word in words:
            if word == targetWord:
                count += 1
    text.close()
    if (procnum == -1 or ret_dict == None):
        return count
    else:
        ret_dict[procnum] = count

def mp_main1():
    manager = mp.Manager()
    return_dict = manager.dict()
    processes = []

    x = len(files)
    i=0
    while (i<x):
        p = mp.Process(target=count_words, args=(files[i],targetWord,i, return_dict ))
        processes.append(p)
        i=i+1

    for p in processes:
        p.start()
    for p in processes:
        p.join()

    count = 0
    for v in return_dict.values():
        count += v
    print(count)

def main():
    count = 0
    for file in files:
        count += count_words(file, targetWord)
    print(count)

files =["mobydick.txt","warAndPeace.txt","eragon.txt","hobbit.txt","toKillAMockingbird.txt","mobydick.txt","warAndPeace.txt","eragon.txt","hobbit.txt","toKillAMockingbird.txt","mobydick.txt","warAndPeace.txt","eragon.txt","hobbit.txt","toKillAMockingbird.txt","mobydick.txt","warAndPeace.txt","eragon.txt","hobbit.txt","toKillAMockingbird.txt","mobydick.txt","warAndPeace.txt","eragon.txt","hobbit.txt","toKillAMockingbird.txt","mobydick.txt","warAndPeace.txt","eragon.txt","hobbit.txt","toKillAMockingbird.txt","mobydick.txt","warAndPeace.txt","eragon.txt","hobbit.txt","toKillAMockingbird.txt","mobydick.txt","warAndPeace.txt","eragon.txt","hobbit.txt","toKillAMockingbird.txt"]
if len(sys.argv) <2:
  print("Usage: python3 filename <wordToFind>")
  exit(1)
targetWord= sys.argv[1]

b4 = time.time()
mp_main1()
after = time.time()
elapsed = after-b4

print("multicore new took "+str(elapsed)+"second")

b4 = time.time()
main()
after = time.time()
elapsed = after-b4
print("serial took "+str(elapsed)+"second")
