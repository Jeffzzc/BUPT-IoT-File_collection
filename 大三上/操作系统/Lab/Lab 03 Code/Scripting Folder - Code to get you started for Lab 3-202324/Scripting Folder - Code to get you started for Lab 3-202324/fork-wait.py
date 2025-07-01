import os, sys

try :
    new_pid = os.fork()
    # fork creates a copy of the current process
    # the process id of the child process is returned if in the parent process
    #0 is returned if in the child process
    #OSError exception is thrown if fork fails (note in POSIX this would return -1)
    #  both processes continue to this point, but when os.fork() returns,
    #  we have to find out if we are in the parent process or
    #  in the child process, both run concurrently!!!

    if (new_pid == 0) :
         print("In child and child pid is", os.getpid())
         print("My parent pid is", os.getppid())
         os._exit(0)
         # exits without calling cleanup handlers, flushing stdio buffers, etc
         # should normally only be used in the child process after a fork()
    else :
         print("Parent: pid",os.getpid(), "and child pid", new_pid)
         # wait for the child process to exit
         # the 'status' variable is updated with the exit status

         # If we pass 0 to os.waitpid, the request is for the status of 
         # any child in the process group of the current process.
         # If we pass -1, the request pertains to any child of the current process.
         _, status = os.waitpid(0, 0)

         # this function gets the exit code from the status
         exitCode = os.WEXITSTATUS(status)
         print("Child exit code is:", exitCode)
except OSError:
    print("fork failed: Could not create a child process")
    sys.exit(1)
