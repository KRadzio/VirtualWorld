from Animal import *

class Antelope(Animal):
    __ANTELOPE_STRENGTH__ = 4
    __ANTELOPE_INITIATIVE__ = 4
    __ANTELOPE_MOVE_RANGE__ = 2
    __COLOR__ = "Brown"
    
    def __init__(self, row, col, world):
        super().__init__(row, col, self.__ANTELOPE_STRENGTH__, self.__ANTELOPE_INITIATIVE__, world)
    
    def getName(self):
        return "Antelope"
    
    def getColor(self):
        return self.__COLOR__   
    
    def reproduce(self, row, col):
        self._world_.setOrganism(row, col, Antelope())

    def specialAbility(self, other):
        rand = randrange(2)
        if (rand == 1):
            if (self._world_.getOrgFromPos(self.getRow() - 1, self.getCol()) == None):
                self.move(self.getRow() - 1, self.getCol())
            elif (self._world_.getOrgFromPos(self.getRow(), self.getCol() + 1) == None):
                self.move(self.getRow(), self.getCol() + 1)
            elif (self._world_.getOrgFromPos(self.getRow() + 1, self.getCol()) == None):
                self.move(self.getRow() + 1, self.getCol())
            elif (self._world_.getOrgFromPos(self.getRow(), self.getCol() - 1) == None):
                self.move(self.getRow(), self.getCol() - 1)
            event = self.getName() + "uciekla przed" + other.getName()
            self._world_.addEvent(event)
            return True
        else:
            self._world_.removeOrganism(self.getRow(), self.getCol())
            self.die()
        return True

    def takeAction(self):
        rand = randrange(4)
        if rand == 0: #gore
            self.move(self.getRow()-self.__ANTELOPE_MOVE_RANGE__ , self.getCol())
        elif rand == 1: #prawo
            self.move(self.getRow() , self.getCol()+self.__ANTELOPE_MOVE_RANGE__)
        elif rand == 2: #dol
            self.move(self.getRow()+self.__ANTELOPE_MOVE_RANGE__ , self.getCol())
        elif rand == 3: # lewo
            self.move(self.getRow() , self.getCol()-self.__ANTELOPE_MOVE_RANGE__)
        else:
            pass