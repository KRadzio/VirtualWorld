#pragma once
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <cmath>
#include "Organism.h"

using namespace std;

class Organism;
class Human;
class World
{
public:
    void passTurn();
    void drawWorld();
    void drawEvents();
    void setSize(int size);
    void setOrganisms();
    void randomOrganism(int xPos, int yPos);
    void clearWorld();
    void sortOrganisms();
    void removeOrganisms();
    void addEvent(string event);
    void addNewOrganism(Organism *parent, int xPos, int yPos);
    void clearEvents();
    bool isHumanAlive() const;
    bool isPositionFree(int x, int y) const;
    bool isPositionInsideWorld(int x, int y) const;
    Organism *getOrganism(int x, int y);
    int getSize() const;
    int getTurn() const;
    World();
    ~World();

private:
    int turn = 1;
    int size;
    Human *hum;
    vector<Organism *> organisms;
    vector<string> events;
};
