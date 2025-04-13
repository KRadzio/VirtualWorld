from Plant import *

class Guarana(Plant):
    __GUARANA_STRENGTH__ = 0
    __STRENGTH_BONUS__ = 3
    __COLOR__ = "Red"
    
    def __init__(self, row, col, world):
        super().__init__(row, col, self.__GUARANA_STRENGTH__,world)
    
    def getColor(self):
        return self.__COLOR__
      
    def getName(self):
      return "Guarana"

    def spread(self, newRow, newCol):
      self._world_.setOrganism(newRow, newCol, Guarana(newRow , newCol, self._world_))

    def specialAbility(self, other):
        other.setExtraStrength(self.__STRENGTH_BONUS__)
        event = other.getName() + " zjada guaranne jego siła wzrasta o " + str(self.__STRENGTH_BONUS__)
        self._world_.addEvent(event)
        self._world_.removeOrganism(self.getRow(), self.getCol())
        self.die()
        return True
    