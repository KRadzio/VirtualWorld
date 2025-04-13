from Plant import *

class Grass(Plant):
    __GRASS_STRENGTH__ = 0
    __COLOR__ = "Green"
    
    def __init__(self, row, col, world):
        super().__init__(row, col, self.__GRASS_STRENGTH__,world)
    
    def getColor(self):
        return self.__COLOR__
    
    def getName(self):
        return "Grass"

    def spread(self, newRow, newCol):
        self._world_.setOrganism(newRow, newCol, Grass(newRow , newCol, self._world_))
