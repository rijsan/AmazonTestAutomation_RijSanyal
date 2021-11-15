from math import factorial

class Pascal:
    def __init__(self, inp):
        self.input = inp
    def print(self):
        for i in range(self.input):
            # for j in range(self.input-i):
            #     print("  ", end = "")
            for j in range(i+1):
                print(factorial(i)//(factorial(j)*factorial(i-j)), end=" ")
            for k in range(self.input-i-1):
                print(0, end=" ")
            print()

class Pattern:
    def __init__(self, inp):
        self.input1 = inp
    def print(self):
        for i in range(self.input1):
            for j in range(self.input1-i):
                print("  ", end = "")
            for k in range(1+(i*2)):
                print("* ", end="")
            print()
        for x in range(self.input1-1,0,-1):
            for y in range(self.input1-x+1):
                print("  ", end="")
            for z in range(2*(x), 1, -1):
                print("* ", end="")
            print()


obj1 = Pascal(3)
obj1.print()
obj2 = Pattern(5)
obj2.print()

