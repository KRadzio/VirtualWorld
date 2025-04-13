#include "Guarana.h"
Guarana::Guarana(World &wR, const int &xPos, const int &yPos)
    : Plant(wR, xPos, yPos, SYMBOL, STRENGTH, AllOrganisms::GUARANA) {this->typeS = "Guarana";}
Guarana::~Guarana() {}

bool Guarana::specialAbility(Organism *other)
{
    other->setStrength(other->getStrength() + STRENGTH_BONUS);
    this->die();
    string event = other->getTypeString() + " zjada guaranne jego siła wzrasta o " + std::to_string(STRENGTH_BONUS);
    this->worldRef.addEvent(event);
    return true;
}