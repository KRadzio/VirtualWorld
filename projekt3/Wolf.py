from Animal import *

class Wolf(Animal):
    __WOLF_STRENGTH__ = 9
    __WOLF_INITIATIVE__ = 5
    __COLOR__ = "darkgray"
    
    def __init__(self, row, col, world):
        super().__init__(row, col, self.__WOLF_STRENGTH__, self.__WOLF_INITIATIVE__, world)
    
    def getName(self):
      return "Wolf"
    
    def getColor(self):
        return self.__COLOR__
    

    def reproduce(self, row, col):
        self._world_.setOrganism(row, col, Wolf())