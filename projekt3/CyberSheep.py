from Animal import *

class CyberSheep(Animal):
    
    __SHEEP_STRENGTH__ = 11
    __SHEEP_INITIATIVE__ = 4
    __COLOR__ = "lightgray"
    
    def __init__(self, row, col, world):
        super().__init__(row, col, self.__SHEEP_STRENGTH__, self.__SHEEP_INITIATIVE__, world)
        
    def specialResistance(self):
        return True
    
    def getName(self):
        return "CyberSheep"
     
    def getColor(self):
        return self.__COLOR__

    def reproduce(self, row, col):
        self._world_.setOrganism(row, col, CyberSheep())
        
    def calcDist(self, pine):
       return pow(self.getRow() - pine.getRow(), 2) + pow(self.getCol() - pine.getCol(), 2)
              
    def takeAction(self):
        pines = self._world_.getPines()    
        if(len(pines) > 0):
            shortesDist = 2137
            closestPine = None
            for i in range(len(pines)):
                currDist = self.calcDist(pines[i])
                if(currDist < shortesDist):
                    shortesDist = currDist
                    closestPine = pines[i]
            if(closestPine.getRow() > self.getRow()):
                self.move(self.getRow()+1 , self.getCol())
            elif closestPine.getRow() < self.getRow(): 
                self.move(self.getRow()-1 , self.getCol())
            elif closestPine.getCol() > self.getCol(): 
                self.move(self.getRow() , self.getCol()+1)
            elif closestPine.getCol() < self.getCol():              
                self.move(self.getRow() , self.getCol()-1)
        else:       
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