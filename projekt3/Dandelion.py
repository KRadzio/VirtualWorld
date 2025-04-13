from Plant import *

class Dandelion(Plant):
    
    __DANDELION_STRENGTH__ = 0
    __COLOR__ = "Yellow"
    
    def __init__(self, row, col, world):
        super().__init__(row, col, self.__DANDELION_STRENGTH__, world)
    
    def getName(self):
        return "Dandelion"
    
    def getColor(self):
        return self.__COLOR__

    def spread(self, newRow, newCol):
        self._world_.setOrganism(newRow, newCol, Dandelion(newRow , newCol, self._world_))

    def takeAction(self):
        for i in range(3):
            random = randrange(100)
            if (random < 10):
                self.trySpread()
   