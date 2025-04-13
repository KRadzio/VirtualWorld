#include "Plant.h"
void Plant::trySpread()
{
     bool spreadIsValid = false;
     int newX;
     int newY;
     while (spreadIsValid == false)
     {
          newX = rand() % this->worldRef.getSize();
          newY = rand() % this->worldRef.getSize();
          if (this->worldRef.isPositionFree(newX, newY))
               spreadIsValid = true;
     }
     Organism *parent = this->worldRef.getOrganism(this->xPos, this->yPos);
     string event = this->getTypeString() + " rozprzestrzenil sie na pole (" + std::to_string(newX) + "," + std::to_string(newY) + ")";
     this->worldRef.addEvent(event);
     this->worldRef.addNewOrganism(parent, newX, newY);
}
void Plant::takeAction()
{
     if (rand() % 100 < 10)
          trySpread();
}

bool Plant::isAnAnimal() const { return false; }

Plant::Plant(World &wR, const int &xPos, const int &yPos, const char &symbol, const int &strength, AllOrganisms type)
    : Organism(wR, xPos, yPos, symbol, strength, PLANT_INITIATIVE, type) {}
Plant::~Plant() {}