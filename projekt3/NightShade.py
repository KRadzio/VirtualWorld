from Plant import *

class NightShade(Plant):
    __NIGHTSHADE_STRENGTH__ = 99
    __COLOR__ = "Blue"
    
    def __init__(self, row, col, world):
        super().__init__(row, col, self.__NIGHTSHADE_STRENGTH__,world)
    
    def getColor(self):
        return self.__COLOR__
      
    def getName(self):
      return "NightShade"

    def spread(self, newRow, newCol):
      self._world_.setOrganism(newRow, newCol, NightShade(newRow , newCol, self._world_))

    def specialAbility(self, other):
      self._world_.removeOrganism(other.getRow(), other.getCol())
      other.die()
      self._world_.removeOrganism(self.getRow(), self.getCol())
      self.die()
      event = other.getName() + " zjada wilcze jagody i umiera"
      self._world_.addEvent(event)
      self._world_.removeOrganism(self.getRow(), self.getCol())
      return True