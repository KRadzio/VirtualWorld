from Animal import *

class Fox(Animal):
    __FOX_STRENGTH__ = 3
    __FOX_INITIATIVE__ = 7
    __COLOR__ = "Orange"
    
    def __init__(self, row, col, world):
        super().__init__(row, col, self.__FOX_STRENGTH__, self.__FOX_INITIATIVE__, world)
    
    def getColor(self):
        return self.__COLOR__
    
    def getName(self):
        return "Fox"

    def reproduce(self, row, col):
        self._world_.setOrganism(row, col, Fox())

    def takeAction(self):
        rand = randrange(4)
        if rand == 0: #gura
            if (self._world_.isInsideWorld(self.getRow() - 1, self.getCol())):
                    if (self._world_.getOrgFromPos(self.getRow() - 1, self.getCol()) != None):
                        other = self._world_.getOrgFromPos(self.getRow() - 1, self.getCol())
                        if (other.getStrength() < self.getStrength()):
                            self.move(self.getRow() - 1, self.getCol())
                    else:
                        self.move(self.getRow() - 1, self.getCol())
        elif rand == 1: #prawo
            if (self._world_.isInsideWorld(self.getRow(), self.getCol() + 1)):
                    if (self._world_.getOrgFromPos(self.getRow(), self.getCol() + 1) != None):
                        other = self._world_.getOrgFromPos(self.getRow(), self.getCol() + 1)
                        if (other.getStrength() < self.getStrength()):
                            self.move(self.getRow() , self.getCol() + 1)
                    else:
                        self.move(self.getRow() , self.getCol() + 1)
        elif rand == 2: #dol
            if (self._world_.isInsideWorld(self.getRow() + 1, self.getCol())):
                    if (self._world_.getOrgFromPos(self.getRow() + 1, self.getCol()) != None):
                        other = self._world_.getOrgFromPos(self.getRow() + 1, self.getCol())
                        if (other.getStrength() < self.getStrength()):
                            self.move(self.getRow() + 1, self.getCol())
                    else:
                        self.move(self.getRow() + 1, self.getCol())
        elif rand == 3: # lewo
            if (self._world_.isInsideWorld(self.getRow(), self.getCol() - 1)):
                    if (self._world_.getOrgFromPos(self.getRow(), self.getCol() - 1) != None):
                        other = self._world_.getOrgFromPos(self.getRow(), self.getCol() - 1)
                        if (other.getStrength() < self.getStrength()):
                            self.move(self.getRow(), self.getCol() - 1)
                    else:
                        self.move(self.getRow(), self.getCol() - 1)
        else:
            pass