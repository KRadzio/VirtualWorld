from tkinter import *
from random import random, randrange

from Organism import Organism
from World import World

from Antelope import Antelope
from CyberSheep import CyberSheep
from Fox import Fox
from Sheep import Sheep
from Wolf import Wolf
from Human import Human
from Grass import Grass
from Dandelion import Dandelion
from Guarana import Guarana
from Pineborscht import Pineborscht
from NightShade import NightShade
from tkinter import *

class Engine(Frame):
    __world__ = None
    __canvas__ = None
    
    def __init__(self):
        self.__world__ = World(30)
        self.populateWorld()
        self.runSymulation()
        
    def setNewWorld(self, size):
        self.__world__ = World(size)   
    
    def populateWorld(self):
        human = Human(self.__world__.size() / 2,self.__world__.size() / 2, self.__world__)
        self.__world__.setOrganism(int(self.__world__.size() / 2), int(self.__world__.size() / 2), human)
        self.__world__.setHuman(human)
        for i in range(self.__world__.size()* 6):
            rand = randrange(10)
            positionIsClear = False
            newRow = 0
            newCol = 0
            while (positionIsClear == False):
                newRow = randrange(self.__world__.size())
                newCol = randrange(self.__world__.size())
                if (self.__world__.isPosEmpty(newRow, newCol) == True):
                   positionIsClear = True
            self.__world__.setOrganism(newRow, newCol, self.createOrganism(rand, newRow, newCol))
    
    def createOrganism(self, random, row, col):
        if random == 0: 
            org = Grass(row, col, self.__world__)
        elif random == 1: 
            org = Dandelion(row, col, self.__world__)
        elif random == 2: 
            org = Guarana(row, col, self.__world__)
        elif random == 3: 
            org = NightShade(row, col, self.__world__)
        elif random == 4: 
            org = Pineborscht(row, col, self.__world__)
        elif random == 5: 
            org = Sheep(row, col, self.__world__)
        elif random == 6: 
            org = Fox(row, col, self.__world__)
        elif random == 7: 
            org = Antelope(row, col, self.__world__)
        elif random == 8: 
            org = Wolf(row, col, self.__world__)
        elif random == 9: 
            org = CyberSheep(row, col, self.__world__)
        else:
            pass
        return org
        
    def printMap(self):
        self.canvas.delete("all")
        map = self.__world__.getMap()
        for i in range(self.__world__.size()):
            for j in range(self.__world__.size()):
                    if(self.__world__.getOrgFromPos(i,j) == None):
                       self.canvas.create_rectangle(30 + j* 30, 35 + i * 30, 30 + 30 * self.__world__.size(),
                                        35 + 30 * self.__world__.size(), fill="White")
                    else:   
                        self.canvas.create_rectangle(30 + j* 30, 35 + i * 30, 30 + 30 * self.__world__.size(),
                                        35 + 30 * self.__world__.size(), fill=map[i][j].getColor())
        self.canvas.pack()    
        
    def sortOrgs(self, orgs):
        orgs = sorted(orgs, key=lambda Organism: -Organism.getInitiative())
        return orgs
    
    def newSymulation(self):
        self.__world__ = World(30)
        self.populateWorld()
        self.printMap()

    def save(self):
        input = Tk()
        e = Entry(input)
        e.pack()

        def callback():
            name = e.get()
            self.saveToFile(name)
            input.destroy()

        b = Button(input, text="Confirm", command=callback)
        b.pack()
        
    def saveToFile(self, name):
        file = open(name, "w")
        file.write(self.__world__.saveToString())
        orgs = self.__world__.getOrgs()
        for i in range(len(orgs)):
            file.write(orgs[i].saveToString() + '\n')           
        file.write('\n')
        events = self.__world__.getEvents()
        for i in range(len(events)):
            file.write(events[i] + '\n')
        file.close()

    def load(self):
        input = Tk()
        e = Entry(input)
        e.pack()

        def callback():
            name = e.get()
            self.loadFromFile(name)
            input.destroy()

        b = Button(input, text="Confirm", command=callback)
        b.pack()
        
    def loadFromFile(self, name):
            file = open(name, "r")
            currLine = file.readline()
            currLine = currLine.split(' ')
            size = int(currLine[0])
            self.setNewWorld(size)
            self.__world__.setTurn(int(currLine[1]))
            currLine = file.readline()
            while (currLine != "\n"):               
                currLine = currLine.split(' ')
                name = currLine[0]
                if(name != "Human"):
                    age = int(currLine[1])
                    row = int(currLine[2])
                    col = int(currLine[3])
                    extraStrength = int(currLine[4])
                    isAlive = bool(currLine[5])
                    org = None
                    if name =="Antelope":  # antylope
                        org = Antelope(row, col, self.__world__)
                    elif name == "CyberSheep":  # cyberSheep
                        org = CyberSheep(row, col, self.__world__)
                    elif name == "Fox":  # fox
                        org = Fox(row, col, self.__world__)
                    elif name == "Sheep":  # Sheep
                        org = Sheep(row, col, self.__world__)
                    elif name == "Wolf":  # Wolf
                        org = Wolf(row, col, self.__world__)
                    elif name == "Guarana":  # Guarana
                        org = Guarana(row, col, self.__world__)
                    elif name == "Pineborscht":  # Pineborscht
                        org = Pineborscht(row, col, self.__world__)
                    elif name == "NightShade":  # NightShade
                        org = NightShade(row, col, self.__world__)
                    elif name == "Dandelion":  # Dandelion
                        org = Dandelion(row, col, self.__world__)
                    elif name == "Grass":  # Grass
                        org = Grass(row, col, self.__world__)                 
                    org.setAge(age)
                    org.setExtraStrength(extraStrength)
                    org.setAlive(isAlive)
                    self.__world__.setOrganism(row, col, org)                                            
                else:
                    age = int(currLine[1])
                    row = int(currLine[2])
                    col = int(currLine[3])
                    extraStrength = int(currLine[4])
                    isAlive = bool(currLine[5])
                    abilityExtraStrength = int(currLine[6]) 
                    abilityCooldown = int(currLine[7])  
                    direction = int(currLine[8])
                    human = Human(row,col, self.__world__)
                    self.__world__.setOrganism(row, col, human) 
                    self.__world__.setHuman(human)
                    self.__world__.getHuman().setAge(age)
                    self.__world__.getHuman().setExtraStrength(extraStrength)
                    self.__world__.getHuman().setAlive(isAlive)
                    self.__world__.getHuman().setAbilityExtraStrength(abilityExtraStrength)
                    self.__world__.getHuman().setAbilityCooldown(abilityCooldown)
                    self.__world__.getHuman().setDirection(direction)
                    
                currLine = file.readline()
            currLine = file.readline()
            while (currLine != ''):
                self.__world__.addEvent(currLine)
                currLine = file.readline()
            file.close()
            self.printMap()
            events = self.__world__.getEvents()
            for i in range(len(events)):
                print(events[i])

    def runTurn(self):
        orgs = self.__world__.getOrgs()
        orgs = self.sortOrgs(orgs)
        for i in range(len(orgs)):
            if(orgs[i].alive() == True):
                orgs[i].takeAction()
            else:
                if(orgs[i]== self.__world__.getOrgFromPos(orgs[i].getRow(), orgs[i].getCol())):
                    self.__world__.removeOrganism(orgs[i].getRow(), orgs[i].getCol())
        for i in range(len(orgs)):
            if(orgs[i].alive() == False):
                if(orgs[i] == self.__world__.getOrgFromPos(orgs[i].getRow(), orgs[i].getCol())):
                    self.__world__.removeOrganism(orgs[i].getRow(), orgs[i].getCol())
        for i in range(len(orgs)):
            orgs[i].incrementAge()
        orgs.clear()      
        self.__world__.incrementTurn()
        self.printMap()
        
        events = self.__world__.getEvents()
        for i in range(len(events)):
            print(events[i])
        
    def specialAbility(self):
        if(self.__world__.getHuman() != None):
            self.__world__.getHuman().activateAbility()
        
    def moveUp(self):
        if(self.__world__.getHuman() != None):
            self.__world__.getHuman().setDirection(0)

    def moveDown(self):
        if(self.__world__.getHuman() != None):
            self.__world__.getHuman().setDirection(2)
        
    def moveRight(self):
        if(self.__world__.getHuman() != None):
            self.__world__.getHuman().setDirection(1)
        
    def moveLeft(self):
        if(self.__world__.getHuman() != None):
            self.__world__.getHuman().setDirection(3)
    
    
    def printButtons(self, window):
        b1 = Button(window, text="New Simulation", command=lambda: self.newSymulation(), height=50, width=50)
        b1.place(bordermode=OUTSIDE, height=30, width=100, x=0, y=0)
        b2 = Button(window, text="Save", command=lambda: self.save(), height=50, width=50)
        b2.place(bordermode=OUTSIDE, height=30, width=100, x=110)
        b3 = Button(window, text="Load", command=lambda: self.load(), height=50, width=50)
        b3.place(bordermode=OUTSIDE, height=30, width=100, x=220)
        b4 = Button(window, text="Run turn", command=lambda: self.runTurn(), height=50, width=50)
        b4.place(bordermode=OUTSIDE, height=30, width=100, x=330)
        b5 = Button(window, text="Activate Ability", command=lambda: self.specialAbility(), height=50, width=50)
        b5.place(bordermode=OUTSIDE, height=30, width=100, x=440)
        b6 = Button(window, text="Move up", command=lambda: self.moveUp(), height=50, width=50)
        b6.place(bordermode=OUTSIDE, height=30, width=100, x=550)
        b7 = Button(window, text="Move down", command=lambda: self.moveDown(), height=50, width=50)
        b7.place(bordermode=OUTSIDE, height=30, width=100, x=660)
        b8 = Button(window, text="Move right", command=lambda: self.moveRight(), height=50, width=50)
        b8.place(bordermode=OUTSIDE, height=30, width=100, x=770)
        b9 = Button(window, text="Move left", command=lambda: self.moveLeft(), height=50, width=50)
        b9.place(bordermode=OUTSIDE, height=30, width=100, x=880)
        
    def runSymulation(self):
        window = Tk()
        window.title("Symulacja Python 193251")
        window.resizable(0, 0)

        self.canvas = Canvas(window, width=1600, height=1000)
        self.canvas.pack()
        self.printMap()
        self.printButtons(window)
        events = self.__world__.getEvents()
        for i in range(len(events)):
            print(events[i])        
        window.mainloop()
        
