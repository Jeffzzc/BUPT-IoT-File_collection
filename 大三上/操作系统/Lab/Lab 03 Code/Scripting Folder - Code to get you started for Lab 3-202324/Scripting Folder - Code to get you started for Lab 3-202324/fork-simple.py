import os

try :
    new_pid = os.fork()
    # fork creates a copy of the current process
    # the process id of the child process is returned if in the parent process
    # 0 is returned if in the child process
    # OSError exception is thrown if fork fails (note in POSIX this would return -1)
    #  both processes continue to this point, but when os.fork() returns,
    #  we have to find out if we are in the parent process or
    #  in the child process, both run concurrently!!!

    if (new_pid == 0) :
        print("In child and child pid is", os.getpid())
        print("My parent pid is", os.getppid())
        os._exit(0)
        # exits without calling cleanup handlers, flushing stdio buffers, etc.
        # should normally only be used in the child process after a fork()
    else :
        print("Parent: pid", os.getpid(), "and child pid", new_pid)
except OSError:
    exit("fork failed - could not create child process")
	
