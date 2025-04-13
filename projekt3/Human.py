from Animal import *

class Human(Animal):
    __STAY__ = -1
    __UP__ = 0
    __RIGHT__ = 1
    __DOWN__ = 2
    __LEFT__ = 3
    __HUMAN_STRENGTH__ = 5
    __HUMAN_INITIATIVE__ = 4
    __abilityExtraStrength__ = 0
    __abilityCooldown__ = 0
    __direction__ = __STAY__
    __COLOR__ = "Pink"
    
    def __init__(self, row, col, world):
        super().__init__(row, col, self.__HUMAN_STRENGTH__, self.__HUMAN_INITIATIVE__, world)
    
    def getColor(self):
        return self.__COLOR__
    
    def getName(self):
        return "Human"
    
    def getStrength(self):
        return self.__HUMAN_STRENGTH__ + self._extraStrength_ + self.__abilityExtraStrength__

    def setDirection(self,__direction__): 
        self.__direction__ = __direction__
        
    def setAbilityExtraStrength(self, abilityExtraStrength):
        self.__abilityExtraStrength__ = abilityExtraStrength
        
    def setAbilityCooldown(self, abilityCooldown):
        self.__abilityCooldown__ = abilityCooldown

    def decrementAbilityExtraStrength(self):
        if (self.__abilityExtraStrength__ > 0):
            self.__abilityExtraStrength__ -=1

    def decrementCooldown(self):
        if (self.__abilityCooldown__ > 0):
            self.__abilityCooldown__ -=1

    def activateAbility(self):
        if (self.__abilityCooldown__ == 0 and self.__abilityExtraStrength__ == 0):
            self.__abilityExtraStrength__ = 5
            self.abil__abilityCooldown__ityCooldown = 10

    def reproduce(self, row, col):
        pass 

    def getAbilityExtraStrength(self):
        return self.__abilityExtraStrength__

    def getAbilityCooldown(self):
        return self.__abilityCooldown__

    def isHuman():
        return True
    
    def die(self):
        self._isAlive_ = False
        self._world_.setHuman(None)

    def takeAction(self):
        self.decrementAbilityExtraStrength()
        self.decrementCooldown()
        if self.__direction__ == self.__UP__: #gore
            self.move(self.getRow()-1 , self.getCol())
        elif self.__direction__ == self.__RIGHT__: #prawo
            self.move(self.getRow() , self.getCol()+1)
        elif self.__direction__ == self.__DOWN__: #dol
            self.move(self.getRow()+1 , self.getCol())
        elif self.__direction__ == self.__LEFT__: # lewo
            self.move(self.getRow() , self.getCol()-1)
        else:
            pass
        self.__direction__ = self.__STAY__
    
    def saveToString(self):
        return  self.getName() + ' ' + str(self._age_) + ' ' + str(int(self._row_))  + ' ' + str(int(self._col_))+ ' ' +  str(self._extraStrength_)+ ' ' + str(self._isAlive_)+ ' ' + str(self.__abilityExtraStrength__)+ ' ' + str(self.__abilityCooldown__)+ ' ' + str(self.__direction__)