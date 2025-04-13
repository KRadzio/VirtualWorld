#include "Grass.h"
Grass::Grass(World &wR, const int &xPos, const int &yPos)
    : Plant(wR, xPos, yPos, SYMBOL, STRENGTH, AllOrganisms::GRASS) {this->typeS = "Trawa";}
Grass::~Grass() {}