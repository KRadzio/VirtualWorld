from random import random, randrange
from Organism import *

class Plant(Organism):
    __CONST_PLANT_INITIATIVWE__ = 0
    
    def __init__(self, row,col, strength, world):
        super().__init__(row,col, strength, self.__CONST_PLANT_INITIATIVWE__, world)
    
    @abstractmethod
    def spread(self, newRow, newCol):
        pass
    
    def trySpread(self):
        spreadIsValid = False
        newRow = 0
        newCol = 0
        while (spreadIsValid == False):
            newRow = randrange(self._world_.size())
            newCol = randrange(self._world_.size())
            if (self._world_.getOrgFromPos(newRow, newCol) == None):
                spreadIsValid = True
        event = self.getName() + " rozprzestrzenil sie na pole" + "(" + str(newRow) + "," + str(newCol) + ")"
        self._world_.addEvent(event)
        self.spread(newRow, newCol)

    def takeAction(self):
        random = randrange(100)
        if (random < 10):
            self.trySpread()
     