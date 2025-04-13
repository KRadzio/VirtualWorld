#include "Sheep.h"

Sheep::Sheep(World &wR, const int &xPos, const int &yPos)
    : Animal(wR, xPos, yPos, SYMBOL, STRENGTH, INITIATIVE, AllOrganisms::SHEEP)
{
    this->typeS = "Owca";
}
Sheep::~Sheep() {}