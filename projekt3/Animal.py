from random import random, randrange
from Organism import *

class Animal(Organism):
    
    def __init__(self, row,col, strength, initiative, world):
        super().__init__(row,col, strength, initiative, world)
    
    def collision(self, other):
            if (self.isTheSameSpecies(other)):
                if (self.getAge() > 4 and other.getAge() > 4):
                    self.findPlace(self.getRow(), self.getCol())
                return False
            elif (other.specialAbility(self)):
                    return True
            else: 
                if (self.getStrength() > other.getStrength()):
                    if(other.getRow() != None and other.getCol() != None):
                        self._world_.removeOrganism(other.getRow(), other.getCol())
                        other.die()
                        event = self.getName() + " zabija " + other.getName()
                        self._world_.addEvent(event)
                        return True
                elif (other.getStrength() > self.getStrength()): 
                    self._world_.removeOrganism(self.getRow(), self.getCol())
                    self.die()
                    event = other.getName() + " zabija " + self.getName()
                    self._world_.addEvent(event)
                    return False
                else: 
                    return False     
    
    def findPlace(self, row, col):
        newRow = -1
        newCol = -1
        if (self._world_.getOrgFromPos(row - 1, col)== None):
            self.reproduce(row - 1, col)
            newRow = row - 1
            newCol = col
        elif (self._world_.getOrgFromPos(row, col + 1)== None): 
            self.reproduce(row, col + 1)
            newRow = row
            newCol = col + 1
        elif (self._world_.getOrgFromPos(row + 1, col)== None) :
            self.reproduce(row + 1, col)
            newRow = row + 1
            newCol = col
        elif (self._world_.getOrgFromPos(row, col - 1)== None): 
            self.reproduce(row, col - 1)
            newRow = row
            newCol = col - 1
        
        if (newRow != -1 and newCol != -1) :
            event = "Pojawil sie nowy " + self.getName() + " na polu (" + str(row) + "," + str(col) + ")"
            self._world_.addEvent(event)
    
    def move(self, newRow, newCol):
        if (self._world_.isInsideWorld(newRow, newCol) == False):
            return
        isMovePossible = True
        if (self._world_.getOrgFromPos(int(newRow), int(newCol)) != None):
            isMovePossible = self.collision(self._world_.getOrgFromPos(int(newRow), int(newCol)))
        if (isMovePossible): 
            self._world_.setOrganism(int(newRow), int(newCol), self)
            self._world_.removeOrganism(self.getRow(), self.getCol())
            self.setCol(newCol)
            self.setRow(newRow)
    
    def takeAction(self):
        rand = randrange(4)
        if rand == 0: #gore
            self.move(self.getRow()-1 , self.getCol())
        elif rand == 1: #prawo
            self.move(self.getRow() , self.getCol()+1)
        elif rand == 2: #dol
            self.move(self.getRow()+1 , self.getCol())
        elif rand == 3: # lewo
            self.move(self.getRow() , self.getCol()-1)
        else:
            pass
        
    def isTheSameSpecies(self, other):
        if(self.getName == other.getName()):
            return True
        else:
            return False
        
    def specialResistance(self):
        return False  
        
    @abstractmethod
    def reproduce(self, row, col):
        pass 