#include "Wolf.h"

Wolf::Wolf(World &wR, const int &xPos, const int &yPos)
    : Animal(wR, xPos, yPos, SYMBOL, STRENGTH, INITIATIVE, AllOrganisms::WOLF)
{
    this->typeS = "Wilk";
}
Wolf::~Wolf() {}