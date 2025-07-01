import os
import time

new_pid = os.fork()

if new_pid == 0:
    # We are in the child process, fork returned 0 
    print(os.getpid(), "(child) was just created by", os.getppid())

    for i in range(0, -1000, -1):
        print("Child:", i)
        # time.sleep(1)
        # seconds to sleep for

        # exits without calling cleanup handlers, flushing stdio buffers, etc.
        # (should normally only be used in the child process after a fork()
    os._exit(0)
else:
    # We are in the parent process, fork returned pid > 0, which is child pid
    print(os.getpid(), "(parent) just created", new_pid)

    for i in range(1000):
        print("Parent:", i)
        # time.sleep(1)
        # seconds to sleep for
