from Plant import *
from Animal import Animal
from CyberSheep import CyberSheep

class Pineborscht(Plant):
    __PINEBORSCHT_STRENGTH__ = 10
    __COLOR__ = "darkgreen"
    
    def __init__(self, row, col, world):
        super().__init__(row, col, self.__PINEBORSCHT_STRENGTH__,world)
    
    def getName(self):
      return "Pineborscht"
    
    def getColor(self):
        return self.__COLOR__

    def spread(self, newRow, newCol):
        self._world_.setOrganism(newRow, newCol, Pineborscht(newRow , newCol, self._world_))

    def specialAbility(self, other):
        if(other.specialResistance() == True):
            self._world_.removeOrganism(self.getRow(), self.getCol())
            self.die()
            event = other.getName() + " zjada barszcz sosnowskiego"
            self._world_.addEvent(event)
            return True
        else:
            self._world_.removeOrganism(other.getRow(), other.getCol())
            other.die()
        self._world_.removeOrganism(self.getRow(), self.getCol())
        self.die()
        event = other.getName() + "zjada barszcz sosnowskiego i umiera"
        self._world_.addEvent(event)
        return True

    def removeNeighbour(self, row, col):
        if(self._world_.isInsideWorld(row, col) == False):
            return
        neighbour = self._world_.getOrgFromPos(row, col)
        if (neighbour != None and isinstance(neighbour, Animal)== True and neighbour.specialResistance() == False):
            event = neighbour.getName() + " zostaje spalony przez barszcz sosnowskiego"
            self._world_.addEvent(event)
            self._world_.removeOrganism(row, col)
            neighbour.die()

    def takeAction(self):
        self.removeNeighbour(self.getRow() - 1, self.getCol()) #gora
        self.removeNeighbour(self.getRow(), self.getCol() + 1) # prawo
        self.removeNeighbour(self.getRow() + 1, self.getCol()) # dol
        self.removeNeighbour(self.getRow(), self.getCol() - 1) # lewo
        random = randrange(100)
        if (random < 10):
            self.trySpread()