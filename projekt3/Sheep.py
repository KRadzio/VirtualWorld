from Animal import *

class Sheep(Animal):
    
    __SHEEP_STRENGTH__ = 4
    __SHEEP_INITIATIVE__ = 4
    __COLOR__ = "Gray"
    
    def __init__(self, row, col, world):
        super().__init__(row, col, self.__SHEEP_STRENGTH__, self.__SHEEP_INITIATIVE__, world)
    
    def getName(self):
      return "Sheep"
    
    def getColor(self):
        return self.__COLOR__

    def reproduce(self, row, col):
        self._world_.setOrganism(row, col, Sheep())