from itertools import combinations

class StringClass:
    #taking input in a constructor
    def __init__(self, inp):
        self.input1 = inp
    #Length() - printing the length of characters of the input
    def Length(self):
        l = len(self.input1)
        print("Length of Input : "+ str(l))
    #Convert() - converting string into a List of Characters
    def Convert(self):
        charList = list(self.input1)
        return charList

#Inheriting the StringClass
class PairsPossible(StringClass):
    def pairs(self):

        cList = StringClass.Convert(self)
        pairs = list(combinations(cList, 2))
        return pairs
    def displayPairs(self):
        allPairs = PairsPossible.pairs(self)
        print("All Possible Pairs : ")
        for i in allPairs:
            print(i[0]+i[1], end=" ")

class SearchCommonElements(StringClass):
    # taking input in a constructor
    def __init__(self, inp1, inp2):
        self.input1 = inp1
        self.input2 = inp2
    def commonElements(self):
        c1List = StringClass.Convert(self.input1)
        c2List = StringClass.Convert(self.input2)
        dict = {}
        for i in c1List:
            if (dict.get(i)):
                dict[i] = int(dict[i]) + 1
            else:
                dict[i] = 1
        for i in c2List:
            if (dict.get(i)):
                dict[i] = int(dict[i]) + 1
            else:
                dict[i] = 1
        print("\n")
        print("Common Elements from 2 strings :")
        print(list(dict.keys()))

class EqualSumPairs(PairsPossible):
    def equalPairs(self):
        numPairs = PairsPossible.pairs(self)
        dict = {}
        for i in numPairs:
            s1 = int(i[0])+int(i[1])
            if(dict.get(s1)):
                dict[s1]=int(dict[s1])+1
            else:
                dict[s1]=1
        print()
        print("Count of Pairs with Unique Count: ")
        countUnique = 0
        for i in dict:
            if(dict[i]==1):
                countUnique = countUnique+1
        print(countUnique)

obj1 = StringClass("12314532")
obj1.Length()
obj2 = PairsPossible("5412786")
obj2.pairs()
obj2.displayPairs()
obj3 = SearchCommonElements(obj1, obj2)
obj3.commonElements()
obj4 = EqualSumPairs("12314532")
obj4.equalPairs()
