
from Pineborscht import Pineborscht

class World:
    __size__ = None
    __map__ = None
    __events__ = None
    __turn__ = 1
    __human__ = None
    
    def __init__(self, size):
        self.__size__ = size
        self.__map__ = [[None for i in range(self.__size__)] for j in range(self.__size__)]
        self.__events__ = []

    def getHuman(self):
        return self.__human__
    
    def setHuman(self, human):
        self.__human__ = human
        
    def isInsideWorld(self, row, col):
        if(row <0 or row >=self.__size__ or col <0 or col >=self.__size__):
            return False
        else:
            return True
        
    def getTurn(self):
        return self.__turn__
    
    def incrementTurn(self):
        self.__turn__ += 1
        
    def size(self):
        return self.__size__
    
    def isPosEmpty(self, row, col):
        if(self.__map__[row][col] == None):
            return True      
        else:  
            return False
    
    def getOrgFromPos(self, row, col):
        return self.__map__[int(row)][int(col)]
    
    def removeOrganism(self, row, col):
        self.__map__[int(row)][int(col)] = None
        
    def getOrgs(self):
        orgs = []
        for i in range(self.__size__):
            for j in range(self.__size__):
                if(self.__map__[i][j] != None):
                        orgs.append(self.__map__[i][j])
        return orgs
    
    def getPines(self):
        pines = []
        for i in range(self.__size__):
            for j in range(self.__size__):
                if(self.__map__[i][j] != None  and isinstance(self.__map__[i][j], Pineborscht)):
                        pines.append(self.__map__[i][j])
        return pines
        
    def setOrganism(self, row, col, Organism):
        self.__map__[row][col] = Organism
        
    def getMap(self):
        return self.__map__
        
    def getEvents(self):
        return self.__events__
    
    def clearEvents(self):
        self.__events__.clear()
        
    def addEvent(self, message):
        self.__events__.append(message)
        
    def setTurn(self, turn):
        self.__turn__ = turn
        
    def saveToString(self):
        return str(self.__size__) + ' ' + str(self.__turn__) + '\n'  