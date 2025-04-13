from abc import *

class Organism(metaclass=ABCMeta):
    _age_ = 1
    _strength_ = 0
    _extraStrength_ = 0
    _initiative_ = 0
    _row_ = None
    _col_ = None
    _isAlive_ = True
    _world_ = None
    
    def __init__(self, row,col, strength, initiative, world):
        self._row_ = row
        self._col_ = col
        self._strength_ = strength
        self._initiative_ = initiative
        self._world_ = world
    
    def getExtraStrength(self):
        return self._extraStrength_
    
    def setExtraStrength(self, extraStrength):
        self._extraStrength_ += extraStrength
        
    
    def getInitiative(self):
        return self._initiative_
    
    def getStrength(self):
        return self._strength_ + self._extraStrength_
    
    def isHuman():
        return False
    
    def setAge(self, age):
        self._age_ = age
    
    def getAge(self):
        return self._age_
    
    def incrementAge(self):
        self._age_ += 1
        
    def getRow(self):
        return self._row_
    
    def setRow(self, row):
        self._row_ = row
    
    def getCol(self):
        return self._col_
    
    def setCol(self, col):
        self._col_ = col
  
    @abstractmethod
    def takeAction(self):
        pass
    
    def specialAbility(self, other):
        return False
    
    def setAlive(self, status):
        self._isAlive_ = status
    
    def alive(self):
        return self._isAlive_
    
    def die(self):
        self._isAlive_ = False
        
    def getWorld(self):
        return self._world_
    
    def setWorld(self, world):
        self._world_ = world
        
    @abstractmethod
    def getColor():
        pass
    
    @abstractmethod
    def getName():
        return None
    
    def saveToString(self):
        return  self.getName() + ' ' + str(self._age_) + ' ' + str(self._row_) + ' ' + str(self._col_ )+ ' ' +  str(self._extraStrength_)+ ' ' + str(self._isAlive_)